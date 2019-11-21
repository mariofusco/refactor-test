/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.test;

import org.drools.core.impl.KnowledgeBaseImpl;
import org.junit.Test;
import org.kie.api.FactHandle;
import org.kie.api.KieBase;
import org.kie.api.KieSession;
import org.kogito.drools.core.impl.KogitoBaseImpl;

import static org.drools.core.proxy.ProxyFactory.proxy;
import static org.junit.Assert.assertEquals;

public class MyTest {

    @Test
    public void test1() {
        KieBase kbase = new KnowledgeBaseImpl( new KogitoBaseImpl() );
        KieSession ksession = kbase.newKieSession();
        FactHandle fh = ksession.insert( "pippo" );
        assertEquals(0, ksession.fireAllRules());
        ksession.remove( fh );
    }

    @Test
    public void testProxy() {
        KieBase kbase = proxy(KieBase.class, new KogitoBaseImpl() );

        KieSession ksession = kbase.newKieSession();
        FactHandle fh = ksession.insert( "pippo" );
        assertEquals(0, ksession.fireAllRules());
        ksession.remove( fh );
    }
}
