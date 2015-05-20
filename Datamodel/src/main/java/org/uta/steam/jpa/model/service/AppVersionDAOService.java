package org.uta.steam.jpa.model.service;

import java.util.List;

import org.uta.steam.jpa.model.AppVersion;

public interface AppVersionDAOService extends AbstractDAOService<AppVersion> {

	List<AppVersion> getVersionsByAppId(long appId);
}
