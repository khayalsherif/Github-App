
package com.example.android.codelabs.paging.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDto


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
