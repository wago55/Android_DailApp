package app.wago.wagochan.dail

import android.net.Uri
import com.google.firebase.storage.StorageReference

data class PicByFireBaseData (
        var picUriLeft: StorageReference,
        var picUriRight: StorageReference
        )

//data class PicByFireBaseDataRight (
//        var picUriRight: StorageReference
//        )


