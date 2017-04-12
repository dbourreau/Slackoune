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
package com.dbourreau.sample.domain.interactor;

import com.dbourreau.sample.domain.executor.PostExecutionThread;
import com.dbourreau.sample.domain.executor.ThreadExecutor;
import com.dbourreau.sample.domain.interactor.GetUserDetails.Params;
import com.dbourreau.sample.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetUserDetailsTest {

    private static final int USER_ID = 123;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private GetUserDetails getUserDetails;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        getUserDetails = new GetUserDetails(mockUserRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetUserDetailsUseCaseObservableHappyCase() {
        getUserDetails.buildUseCaseObservable(Params.forUser(USER_ID));

        verify(mockUserRepository).user(USER_ID);
        verifyNoMoreInteractions(mockUserRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }

    @Test
    public void testShouldFailWhenNoOrEmptyParameters() {
        expectedException.expect(NullPointerException.class);
        getUserDetails.buildUseCaseObservable(null);
    }
}