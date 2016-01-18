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
package solutions.siren.join;

import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.PluginsService;
import org.elasticsearch.test.ESIntegTestCase;
import solutions.siren.join.action.coordinate.FilterJoinCache;
import org.elasticsearch.common.settings.Settings;
import org.junit.Before;

import java.util.Arrays;
import java.util.Collection;

import static org.elasticsearch.common.settings.Settings.settingsBuilder;

public class FilterJoinTestCase extends ESIntegTestCase {

  @Override
  protected Settings nodeSettings(int nodeOrdinal) {
    return settingsBuilder()
      .put("path.data", "./target/elasticsearch-test/data/")
      .put(super.nodeSettings(nodeOrdinal)).build();
  }

  /**
   * Elasticsearch test framework will randomly use a transport client instead of a node.
   * It is necessary to explicitly tell Elasticsearch to load plugins for transport clients, otherwise
   * tests will fail randomly.
   */
  @Override
  protected Settings transportClientSettings() {
    return settingsBuilder()
      .put(super.transportClientSettings()).build();
  }

  @Override
  protected Collection<Class<? extends Plugin>> nodePlugins() {
    return Arrays.<Class<? extends Plugin>> asList(FilterJoinPlugin.class);
  }

  @Override
  protected Collection<Class<? extends Plugin>> transportClientPlugins() {
    return nodePlugins();
  }

  @Before
  public void beforeTest() throws Exception {
    logger.info("Invalidate filter join cache before test");
    FilterJoinCache.getInstance().invalidateAll();
  }

}
