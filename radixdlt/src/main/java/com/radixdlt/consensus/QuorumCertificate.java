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

import com.radixdlt.crypto.Signatures;

import java.util.Objects;

public final class QuorumCertificate {
	private final Vote vote;

	public QuorumCertificate(Vote vote) {
		this.vote = Objects.requireNonNull(vote);
	}

	public long getRound() {
		return vote.getRound();
	}

	public Signatures signatures() {
		return this.vote.signatures();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof QuorumCertificate)) {
			return false;
		}

		QuorumCertificate qc = (QuorumCertificate) o;
		return Objects.equals(qc.vote, this.vote);
	}

	@Override
	public int hashCode() {
		return vote.hashCode();
	}
}
