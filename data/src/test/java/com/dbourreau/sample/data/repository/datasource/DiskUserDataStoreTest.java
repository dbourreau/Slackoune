/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dbourreau.sample.data.repository.datasource;

import com.dbourreau.sample.data.cache.UserCache;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DiskUserDataStoreTest {

    private static final int FAKE_USER_ID = 11;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private DiskUserDataStore diskUserDataStore;
    @Mock
    private UserCache mockUserCache;

    @Before
    public void setUp() {
        diskUserDataStore = new DiskUserDataStore(mockUserCache);
    }

    @Test
    public void testGetUserEntityListUnsupported() {
        expectedException.expect(UnsupportedOperationException.class);
        diskUserDataStore.userEntityList();
    }

    @Test
    public void testGetUserEntityDetailesFromCache() {
        diskUserDataStore.userEntityDetails(FAKE_USER_ID);
        verify(mockUserCache).get(FAKE_USER_ID);
    }
}
