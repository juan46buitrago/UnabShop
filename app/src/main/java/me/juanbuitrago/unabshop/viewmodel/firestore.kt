package me.juanbuitrago.unabshop.viewmodel


import com.google.firebase.firestore.FirebaseFirestore

import me.juanbuitrago.unabshop.model.Producto

class Firestore {
    fun agregarProducto(
        firestore: FirebaseFirestore,
        producto: Producto,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("productos")
            .add(producto)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun obtenerProductos(
        firestore: FirebaseFirestore,
        onSuccess: (List<Producto>) -> Unit
    ) {
        firestore.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.map { doc ->
                    Producto(
                        id = doc.id,
                        nombre = doc.getString("nombre") ?: "",
                        descripcion = doc.getString("descripcion") ?: "",
                        precio = doc.getDouble("precio") ?: 0.0
                    )
                }
                onSuccess(lista)
            }
    }

    fun eliminarProducto(
        firestore: FirebaseFirestore,
        id: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("productos")
            .document(id)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
}