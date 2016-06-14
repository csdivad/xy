package hu.csdivad.xy.vaadin;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;

public class XySystemMessageProvider implements SystemMessagesProvider {
	private static final long serialVersionUID = -3076599677717554088L;

	@Override
	public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
		CustomizedSystemMessages msgs = new CustomizedSystemMessages();
		msgs.setSessionExpiredURL("");
		msgs.setSessionExpiredNotificationEnabled(false);
		return msgs;
	}
}
