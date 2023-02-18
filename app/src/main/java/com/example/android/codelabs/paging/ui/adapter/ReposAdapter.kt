/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.example.android.codelabs.paging.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDto

/**
 * Adapter for the list of repositories.
 */
class ReposAdapter : ListAdapter<GithubRepoLocalDto, RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GithubRepoLocalDto>() {
            override fun areItemsTheSame(oldItem: GithubRepoLocalDto, newItem: GithubRepoLocalDto): Boolean =
                oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: GithubRepoLocalDto, newItem: GithubRepoLocalDto): Boolean =
                oldItem == newItem
        }
    }
}
