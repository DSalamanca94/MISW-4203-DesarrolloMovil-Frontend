import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("birthDate") val birthDate: String, // Puedes utilizar un tipo más específico si lo prefieres, como LocalDate.
    @SerializedName("albums") val albums: List<Album>
)

data class Album(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("releaseDate") val releaseDate: String, // Puedes utilizar un tipo más específico, como LocalDate, si lo deseas.
    @SerializedName("description") val description: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("recordLabel") val recordLabel: String,
    @SerializedName("performerPrizes") val performerPrizes: List<PerformerPrize>
)

data class PerformerPrize(
    @SerializedName("id") val id: Int,
    @SerializedName("premiationDate") val premiationDate: String // Puedes utilizar un tipo más específico si lo prefieres, como LocalDate.
)
