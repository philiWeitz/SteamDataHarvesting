package org.uta.steam.jpa.service;

import java.util.List;

import org.uta.steam.jpa.model.AppDLC;

public interface AppDlcDAOService extends AbstractDAOService<AppDLC> {

	List<AppDLC> getDlcsByAppId(long appId);
}
