import React, { useState } from "react";
import axios from "axios";
import { CreateDishStyles } from "../../styled-components/Admin.styled";
import image from "./icon/Union.png";
import { useGetCategoriesQuery } from "../../redux/query/FoodInfo.query.js";

const CreateDish = () => {
  const baseUrl =
    "https://c9-20-javareact-fulltime-production.up.railway.app/api";

  const [discount, setdiscount] = React.useState(false);
  const { data: cat, validado } = useGetCategoriesQuery();
  //Este array es momentaneo hasta poder consumir esta informacion de la API
  let categories = cat._embedded.dishCategories;

  const [datos, setDatos] = useState({
    name: "",
    description: "",
    price: "",
    categoryId: "",
  });
  const [image, setImage] = React.useState({ imagen: null });
  const [token, setToken] = React.useState("");

  React.useEffect(() => {
    const tokeny = localStorage.getItem("accessToken");
    if (tokeny) {
      setToken(tokeny);
    }
  }, []);

  const handleChange = (e) => {
    if (e.target.name === "categoryId") {
      setDatos({
        ...datos,
        categoryId: e.target.options[e.target.selectedIndex].id,
      });
    } else {
      setDatos({ ...datos, [e.target.name]: e.target.value });
    }
  };

  const handleFileInput = (e) => {
    setImage({ image: e.target.files[0] });
  };

  const handleUploadClick = () => {
    if (datos.image) {
      console.log("entro al if");
      return;
    }

    // Es muy importante que el objeto que el user (dish o dish category) se convierta a un blob
    // y se especifique el tipo de contenido application/json
    const blob = new Blob([JSON.stringify(datos)], {
      type: "application/json",
    });

    const formData = new FormData();
    formData.set("dish", blob);
    formData.set("image", image); // Se agrega la imagen del dish o category

    axios
      .post(`${baseUrl}/dishes`, formData, {
        headers: {
          "Content-Type": "multipart/form-data", // Importante que se especifique multipart/form-data en la petición
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        alert("Platillo fue creado");
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
        alert("Error al crear platillo: ", err.message);
      });

    setDatos({ name: "", description: "", price: "", categoryId: "" });
    document.getElementById("fileImage").value = "";
  };

  return (
    <>
      <CreateDishStyles>
        <p>Agregar plato</p>

        <label htmlFor="name">Nombre del plato</label>
        <br />
        <input
          placeholder="Ingrese el nombre del plato"
          required
          type="text"
          id="name"
          className="name"
          name="name"
          onChange={handleChange}
          value={datos.name}
        />
        <br />

        <div className="description">
          <label htmlFor="description">Descripcion</label>
        </div>
        <input
          placeholder="Ingrese la descripcion del plato"
          required
          type="text"
          name="description"
          onChange={handleChange}
          id="description"
          className="description"
          value={datos.description}
        />

        <div className="image_import">
          <label htmlFor="image">Imagen</label>
          <label className="import" htmlFor="price">
            Importe
          </label>
        </div>
        <div className="image_price">
          <label className="inputImage" htmlFor="image">
            Inserte imagen del producto
            <img src={image} alt="" />
          </label>
          <input
            placeholder="Ingrese cantidad"
            required
            className="price"
            name="price"
            onChange={handleChange}
            type="text"
            id="price"
            value={datos.price}
          />
        </div>
        <input
          required
          placeholder=""
          type="file"
          id="fileImage"
          className="image"
          name="imagen"
          onChange={handleFileInput}
        />

        <div className="categoryDiv"></div>
        <label htmlFor="category">Categoria</label>
        <select
          required
          onChange={handleChange}
          name="categoryId"
          id="selector"
          className="category"
        >
          <option value="" disabled selected hidden>
            Selecciona la categoria
          </option>
          {categories.map((category) => (
            <option key={category.id} value={category.name} id={category.id}>
              {category.name}
            </option>
          ))}
        </select>
        <br />
        <div className="container">
          <div className="promotions">
            <label className="checkbox" htmlFor="promotion">
              <input
                className="checkboxInput"
                type="checkbox"
                name=""
                id="promotion"
                onChange={(e) => {
                  if (discount == false) {
                    setdiscount(true);
                  } else setdiscount(false);
                }}
              />
              <span></span>
              Promociones
            </label>
            {discount == true && (
              <>
                <div className="discount">
                  <label htmlFor="">Importe</label>
                  <input
                    className="discountInput"
                    placeholder="Ingrese descuento"
                    type="text"
                  />
                </div>
              </>
            )}
          </div>
          <div className="button">
            <button onClick={handleUploadClick}>Confirmar</button>
          </div>
        </div>
      </CreateDishStyles>
    </>
  );
};

export default CreateDish;
