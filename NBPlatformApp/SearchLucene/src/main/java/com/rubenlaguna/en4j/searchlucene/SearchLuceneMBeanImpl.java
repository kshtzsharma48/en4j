/*
 *  Copyright (C) 2010 Ruben Laguna <ruben.laguna@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.rubenlaguna.en4j.searchlucene;

import java.util.concurrent.ThreadPoolExecutor;
import org.apache.commons.math.stat.descriptive.SynchronizedDescriptiveStatistics;

/**
 * Class SearchLuceneMBeanImpl
 *
 * @author Ruben Laguna <ruben.laguna@gmail.com>
 */
public class SearchLuceneMBeanImpl implements SearchLuceneMBeanImplMBean {

    private ThreadPoolExecutor threadPoolExecutor;

    private SynchronizedDescriptiveStatistics searchTime = new SynchronizedDescriptiveStatistics(10);

    public SearchLuceneMBeanImpl() {
    }

    /**
     * Get number of documents waiting to be indexed
     */
    public int getIndexQueueSize() {
        if (null == threadPoolExecutor) {
            return 0;
        }
        return threadPoolExecutor.getQueue().size();
    }

    /**
     * Get number of actived threads
     */
    public int getActiveThreads() {
        if (null == threadPoolExecutor) {
            return 0;
        }
        return threadPoolExecutor.getActiveCount();
    }

    void setThreadPoolExecutor(ThreadPoolExecutor RP) {
        this.threadPoolExecutor = RP;
    }

    /**
     * Get Average time to complete a search in ms
     */
    public double getAverageSearchTime() {
        return searchTime.getMean();
    }

    public void sampleSearchTime(long i) {
        searchTime.addValue(i);
    }
}


