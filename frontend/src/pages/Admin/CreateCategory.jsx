import React from "react";
import axios from "axios";
import { CategoryListStyles } from "../../styled-components/Admin.styled";
import icoimg from "./icon/Union.png";

export default function CreateCategory() {
  const baseUrl =
    "https://c9-20-javareact-fulltime-production.up.railway.app/api";
  const [image, setImage] = React.useState({ imagen: null });
  const [nombre, setNombre] = React.useState({ name: "" });
  const [token, setToken] = React.useState("");

  React.useEffect(() => {
    const tokeny = localStorage.getItem("accessToken");
    if (tokeny) {
      setToken(tokeny);
    }
  }, []);

  const handleChange = (e) => {
    setNombre({ name: e.target.value });
  };

  const handleFileInput = (e) => {
    setImage({ imagen: e.target.files[0] });
  };

  const handleUploadClick = () => {
    if (!image) {
      return;
    }

    // Es muy importante que el objeto que el user (dish o dish category) se convierta a un blob
    // y se especifique el tipo de contenido application/json
    const blob = new Blob([JSON.stringify(nombre)], {
      type: "application/json",
    });

    const formData = new FormData();
    formData.set("category", blob);
    formData.set("image", image); // Se agrega la imagen del dish o category

    axios
      .post(`${baseUrl}/categories`, formData, {
        headers: {
          "Content-Type": "multipart/form-data", // Importante que se especifique multipart/form-data en la petición
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        alert("La cartegoría fue creada");
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
        alert("Error al crear categoría: ", err.message);
      });

    setNombre({ name: "" });
    document.getElementById("fileImage").value = "";
  };

  return (
    <CategoryListStyles>
      <div className="createCategory">
        <p>Crear Categoría</p>
        <label htmlFor="nombreCategoria">Nombre:</label>
        <input
          type="text"
          placeholder="Ingrese el nombre de la categoría"
          className="name"
          id="nombreCategoria"
          value={nombre.name}
          name={"nombre"}
          onChange={(e) => handleChange(e)}
        />

        <div className="image_button">
          <div className="image_import">
            <label htmlFor="image">Imagen</label>
          </div>
          <div className="image_price">
            <label className="inputImage" htmlFor="image">
              Inserte imagen de la categoria
              <img src={icoimg} alt="" />
            </label>
            <div className="button">
              <button onClick={handleUploadClick}>Confirmar</button>
            </div>
          </div>
          <input
            required
            placeholder="hola"
            type="file"
            id="fileImage"
            className="image"
            name="imagen"
            onChange={handleFileInput}
          />
        </div>
      </div>
    </CategoryListStyles>
  );
}
