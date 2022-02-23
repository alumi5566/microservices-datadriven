/*
** OKafka Java Client version 0.8.
**
** Copyright (c) 2019, 2020 Oracle and/or its affiliates.
** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.
*/

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.oracle.okafka.clients.admin;

import java.util.Collection;
import java.util.Map;

import org.oracle.okafka.common.KafkaFuture;
import org.oracle.okafka.common.annotation.InterfaceStability;

/**
 * The result of the {@link AdminClient#deleteConsumerGroups(Collection)} call.
 *
 * The API of this class is evolving, see {@link AdminClient} for details.
 */
@InterfaceStability.Evolving
public class DeleteConsumerGroupsResult {
    private final Map<String, KafkaFuture<Void>> futures;

    DeleteConsumerGroupsResult(final Map<String, KafkaFuture<Void>> futures) {
        this.futures = futures;
    }

    /**
     * Return a map from group id to futures which can be used to check the status of
     * individual deletions.
     */
    public Map<String, KafkaFuture<Void>> deletedGroups() {
        return futures;
    }

    /**
     * Return a future which succeeds only if all the consumer group deletions succeed.
     */
    public KafkaFuture<Void> all() {
        return KafkaFuture.allOf(futures.values().toArray(new KafkaFuture[0]));
    }
}
