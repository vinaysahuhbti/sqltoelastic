package com.vs.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vs.bean.Parent;
import com.vs.repo.S2ERepo;
import com.vs.util.Messages;
import com.vs.util.ResultSetConvertor;

@EnableScheduling
@Service
public class S2EService<T extends Parent> {

	@Autowired
	DataSource datasource;

	@Autowired
	private S2ERepo<Parent> s2erepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${com.vs.table}")
	private String table;

	private final String QUERY = "select * from " + table +" with NO(LOCK)";
	private final String CQUERY = "select count(*) as total from " + table+" with NO(LOCK)";

	private AtomicInteger recorddone = new AtomicInteger(0);
	private int totalrecord = 0;

	public void storeData() {
		Messages.welcomeMessage();
		totalrecord = getTotalCount();
		Messages.totalRecord(totalrecord);
		ResultSetConvertor<T> convertor = new ResultSetConvertor<>();
		jdbcTemplate.query(QUERY, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Parent obj = convertor.convert(rs);
					s2erepo.save(obj);
					recorddone.incrementAndGet();
				}
			}
		});
	}

	private int getTotalCount() {
		return jdbcTemplate.queryForObject(CQUERY, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int s = rs.getInt("total");
				return s;
			}

		});
	}

	@Scheduled(fixedDelay = 500)
	public void printMessgge() {
		Messages.processedRecord(recorddone.get(), totalrecord);
	}

	@PostConstruct
	public void run() {
		storeData();
	}
}
