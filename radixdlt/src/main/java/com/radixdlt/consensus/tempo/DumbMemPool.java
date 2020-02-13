package com.radixdlt.consensus.tempo;

import com.google.inject.Inject;
import com.radixdlt.common.Atom;
import com.radixdlt.middleware2.converters.AtomToBinaryConverter;
import com.radixdlt.store.LedgerEntry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class DumbMemPool implements MemPool {
	private final AtomToBinaryConverter atomToBinaryConverter;
	private final BlockingDeque<Atom> parkedAtoms;

	@Inject
	public DumbMemPool(AtomToBinaryConverter atomToBinaryConverter) {
		this.atomToBinaryConverter = atomToBinaryConverter;
		this.parkedAtoms = new LinkedBlockingDeque<>();
	}

	@Override
	public LedgerEntry takeNextEntry() throws InterruptedException {
		Atom atom = parkedAtoms.take();
		return new LedgerEntry(atomToBinaryConverter.toLedgerEntryContent(atom), atom.getAID());
	}


	@Override
	public void addAtom(Atom atom) {
		parkedAtoms.add(atom);
	}
}
