/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews

import android.content.Context
import co.edu.udea.compumovil.gr08_20232.lab2.data.AppContainer
import co.edu.udea.compumovil.gr08_20232.lab2.data.interests.InterestsRepository
import co.edu.udea.compumovil.gr08_20232.lab2.data.interests.impl.FakeInterestsRepository
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.PostsRepository
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.impl.BlockingFakePostsRepository

class TestAppContainer(private val context: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy {
        BlockingFakePostsRepository()
    }

    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }
}
