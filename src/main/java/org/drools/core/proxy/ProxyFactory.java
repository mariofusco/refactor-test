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

package org.drools.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.drools.core.proxy.ClassUtil.findMethod;

public class ProxyFactory {

    interface Delegate {
        Object getDelegate();
    }

    public static <T> T proxy(Class<T> clazz, Object delegate) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class<?>[] { clazz, Delegate.class },
                new Handler(delegate));
    }

    public static class Handler implements InvocationHandler, Delegate {

        private final Object delegate;

        public Handler( Object delegate ) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
            if (method.getDeclaringClass() == Delegate.class) {
                return delegate;
            }

            String methodName = method.getName();
            if (methodName.equals( "newKieSession" )) {
                methodName = "newSession";
            }

            Method m;
            if (args != null) {
                Class[] argsType = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Delegate) {
                        args[i] = (( Delegate ) args[i]).getDelegate();
                    }
                    argsType[i] = args[i].getClass();
                }
                m = findMethod( delegate.getClass(), methodName, argsType);
            } else {
                m = delegate.getClass().getMethod( methodName );
            }

            Object result = m.invoke( delegate, args );
            if (result == null) {
                return null;
            }
            return method.getReturnType().isInterface() ?
                    proxy(method.getReturnType(), result) :
                    result;
        }

        @Override
        public Object getDelegate() {
            return delegate;
        }
    }
}
