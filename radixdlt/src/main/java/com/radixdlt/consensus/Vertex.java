/*
 *  (C) Copyright 2020 Radix DLT Ltd
 *
 *  Radix DLT Ltd licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License.  You may obtain a copy of the
 *  License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *  either express or implied.  See the License for the specific
 *  language governing permissions and limitations under the License.
 */

package com.radixdlt.consensus;

import com.radixdlt.common.Atom;
import com.radixdlt.crypto.Hash;
import com.radixdlt.crypto.Signatures;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Vertex in the BFT Chain
 */
public final class Vertex {
	private final QuorumCertificate qc;
	private final long round;
	private final Atom atom;

	public Vertex(QuorumCertificate qc, long round, Atom atom) {
		if (round < 0) {
			throw new IllegalArgumentException("round must be >= 0 but was " + round);
		}
		this.round = round;
		this.qc = qc;
		this.atom = atom;
	}

	public QuorumCertificate getQc() {
		return qc;
	}

	public long getRound() {
		return round;
	}

	public Atom getAtom() {
		return atom;
	}

	public Signatures signatures() {
		return this.qc.signatures();
	}

	public Hash hash() {
		ByteBuffer buffer = ByteBuffer.allocate(32 + 4 + Long.BYTES);
		buffer.put(atom.getHash().toByteArray());
		// TODO use `(sha)hash` of `qc` rather than `hashCode`
		buffer.putInt(qc.hashCode());
		buffer.putLong(round);
		buffer.flip();
		return new Hash(Hash.hash256(buffer.array()));
	}

	@Override
	public int hashCode() {
		return Objects.hash(qc, round, atom);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Vertex)) {
			return false;
		}

		Vertex v = (Vertex) o;
		return v.round == round && Objects.equals(v.atom, this.atom)
			&& Objects.equals(v.qc, this.qc);
	}
}
