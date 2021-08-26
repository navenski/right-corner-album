package com.navektest.core_ui.canceller

import android.view.View
import android.widget.ImageView
import kotlinx.coroutines.Job
import java.lang.ref.WeakReference

/**
 * Store [Job] into HashMap
 * Cancel [Job]
 */
internal class ImageJobsCanceller {

    private val map = HashMap<Int, WeakReference<Job>>()

    /**
     * Store a job in hashMap
     * @param view the imageview that have an executing [Job]
     */
    fun addJob(view: ImageView, job: Job) {
        map[view.hashCode()] = WeakReference(job)
    }

    /**
     * Cancel the [Job] launched with the corresponding view
     */
    fun cancelJob(view: View) {
        map[view.hashCode()]
            ?.get()
            ?.let { job ->
                if (job.isActive)
                    job.cancel()
            }
    }
}