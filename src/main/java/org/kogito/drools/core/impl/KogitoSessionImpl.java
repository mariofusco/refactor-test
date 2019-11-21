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

package org.kogito.drools.core.impl;

import org.kogito.api.FactHandle;
import org.kogito.api.KogitoSession;

public class KogitoSessionImpl implements KogitoSession {

    @Override
    public int fireAllRules() {
        return 0;
    }

    @Override
    public FactHandleImpl insert( Object o ) {
        return new FactHandleImpl(o);
    }

    @Override
    public void remove( FactHandle fh ) { }
}
