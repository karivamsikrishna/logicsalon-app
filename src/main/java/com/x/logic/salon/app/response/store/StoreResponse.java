package com.x.logic.salon.app.response.store;

import com.x.logic.salon.app.data.modal.Store;
import com.x.logic.salon.app.message.Message;

public class StoreResponse {

	private Store store;
	private Message message;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
