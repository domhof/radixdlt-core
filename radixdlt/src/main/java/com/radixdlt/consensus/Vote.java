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

import com.radixdlt.crypto.*;

import java.util.Objects;

/**
 * Represents a vote on a vertex
 */
public final class Vote {
	private final Hash hash;
	private final long round;
	private final Signatures signatures;

	/**
	 * Create a vote for a given round with a certain hash.
	 * Note that the hash must reflect the given round.
	 * This is a temporary method as Vote will be expanded to maintain this invariant itself.
	 */
	public Vote(long round, Hash hash) {
		this.round = round;
		this.hash = Objects.requireNonNull(hash, "'hash' is required");
		this.signatures = Signatures.defaultEmptySignatures();
	}

	/**
	 * Create a vote for a given round with a certain hash and signatures (by replicas) of the hash.
	 * Note that the hash must reflect the given round.
	 * This is a temporary method as Vote will be expanded to maintain this invariant itself.
	 */
	public Vote(long round, Hash hash, Signatures signatures) {
		this.round = round;
		this.hash = Objects.requireNonNull(hash, "'hash' is required");
		this.signatures = Objects.requireNonNull(signatures, "'signatures' is required");
	}

	/**
	 * Create a vote for a given round with a certain hash and a signature (by a replica) of the hash.
	 * Note that the hash must reflect the given round.
	 * This is a temporary method as Vote will be expanded to maintain this invariant itself.
	 */
	public Vote(long round, Hash hash, Signature signature, ECPublicKey publicKey) {
		this(round, hash, Signatures.defaultSingle(publicKey, signature));
	}

	public long getRound() {
		return round;
	}

	public Signatures signatures() {
		return signatures;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Vote vote = (Vote) o;
		return round == vote.round && hash.equals(vote.hash) && signatures.equals(vote.signatures);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hash, round, signatures);
	}


	@Override
	public String toString() {
		return String.format(
				"%s{hash=%s, round=%s, signatures:%s}", getClass().getSimpleName(),
				String.valueOf(this.hash),
				String.valueOf(round),
				String.valueOf(signatures)
		);
	}
}
