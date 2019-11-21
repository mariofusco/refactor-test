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

package org.drools.core.impl;

import org.kie.api.FactHandle;
import org.kie.api.KieSession;
import org.kogito.drools.core.impl.KogitoSessionImpl;

public class StatefulKnowledgeSessionImpl implements KieSession {

    private final KogitoSessionImpl delegate;

    public StatefulKnowledgeSessionImpl( KogitoSessionImpl delegate ) {
        this.delegate = delegate;
    }

    @Override
    public int fireAllRules() {
        return delegate.fireAllRules();
    }

    @Override
    public FactHandle insert( Object o ) {
        return new DefaultFactHandle( delegate.insert( o ) );
    }

    @Override
    public void remove( FactHandle fh ) {
        delegate.remove( (( DefaultFactHandle ) fh).delagate );
    }
}
