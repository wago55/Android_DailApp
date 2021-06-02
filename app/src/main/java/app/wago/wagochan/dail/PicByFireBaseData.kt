package app.wago.wagochan.dail

import android.net.Uri
import com.google.firebase.storage.StorageReference

data class PicByFireBaseData (
        val picUriLeft: StorageReference,
        val picUriRight: StorageReference
        )


