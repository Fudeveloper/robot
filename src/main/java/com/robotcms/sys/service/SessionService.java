package com.robotcms.sys.service;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.robotcms.sys.domain.UserOnline;

/**
 * <pre>
 * </pre>
 * |
 */
@Service
public interface SessionService {
	List<UserOnline> list();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
