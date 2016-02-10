/**
 * Copyright (c) 2015, SIREn Solutions. All Rights Reserved.
 *
 * This file is part of the SIREn project.
 *
 * SIREn is a free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * SIREn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package solutions.siren.join.action.terms.collector;

import org.elasticsearch.index.fielddata.IndexFieldData;
import org.elasticsearch.indices.breaker.CircuitBreakerService;
import org.elasticsearch.search.internal.SearchContext;

/**
 * Collects integer terms for a given field based on a {@link HitStream}.
 */
public class IntegerTermsCollector extends TermsCollector {

  public IntegerTermsCollector(final IndexFieldData indexFieldData, final SearchContext context,
                               final CircuitBreakerService breakerService) {
    super(indexFieldData, context, breakerService);
  }

  @Override
  protected TermsSet newTermsSet(final int expectedElements, final CircuitBreakerService breakerService) {
    return new IntegerTermsSet(expectedElements, breakerService);
  }

}
