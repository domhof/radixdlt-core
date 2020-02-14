package com.radixdlt.consensus;

import java.util.function.Consumer;

public interface Pacemaker {
	// TODO: Change to reactive call
	void addCallback(Consumer<Void> callback);
	void start();
}
