import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Loader } from "../../../../../components";
import {
  useAddNewDataUserMutation,
  useGetUserInfoQuery,
} from "../../../../../redux/query/FoodInfo.query";
import { setAddress } from "../../../../../redux/slice/UserData.slice";
import { MyDataStyle } from "../../../styled-components/MyAccountComponent";

export default function MisDatos() {
  const baseUrl =
    "https://c9-20-javareact-fulltime-production.up.railway.app/api";
  const [id, setId] = useState([]);
  const [token, setToken] = useState([]);
  const [res, setRes] = useState([]);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    const id = JSON.parse(localStorage.getItem("id"));
    if (id) {
      setId(id);
    }
  }, []);

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    if (token) {
      setToken(token);
    }
    axios
      .get(`${baseUrl}/addresses/`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setRes(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const { data: post, isFetching, isSuccess } = useGetUserInfoQuery(id);
  // const { data: ready, isProccess, isReady } = useGetAddressInfoQuery(token);
  const [addNewDataUser, { isLoading }] = useAddNewDataUserMutation();
  const canSave = !isLoading;

  const [NameAndSurname, setNameAndSurname] = useState("");
  const [postalCode, setPostalCode] = useState("");
  const [location, setLocation] = useState("");
  const [street, setStreet] = useState("");
  const [number, setNumber] = useState("");
  const [floor, setFloor] = useState("");
  const [contact, setContact] = useState("");
  const [email, setEmail] = useState("");
  const [moreDetails, setmoreDetails] = useState("");

  // setTimeout(() => {
  //   setNameAndSurname(post.name);
  // }, [5000]);

  useEffect(() => {
    setNameAndSurname(post?.name);
    setEmail(post?.email);
    setPostalCode(res?._embedded?.addresses[0].zipCode);
    setLocation(res?._embedded?.addresses[0].city);
    setStreet(res?._embedded?.addresses[0].street);
    setNumber(res?._embedded?.addresses[0].number);
    // OJO
    setFloor(res?._embedded?.addresses[0].country);
    setContact(res?._embedded?.addresses[0].state);

    dispatch(
      setAddress(
        street + " " + number + " " + floor + " " + location + " " + postalCode
      )
    );
  }, [isSuccess, res]);

  const submit = async (e) => {
    e.preventDefault();
    setTimeout(() => {
      navigate("/");
    }, [500]);
    if (res?._embedded === undefined) {
      // ! post address
      axios.post(
        `${baseUrl}/addresses/`,
        {
          street,
          number,
          city: location,
          state: contact,
          country: floor,
          zipCode: postalCode,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      dispatch(
        setAddress(
          street +
            " " +
            number +
            " " +
            floor +
            " " +
            location +
            " " +
            postalCode
        )
      );
    }

    // ! put name email
    await axios.put(
      `${baseUrl}/users/${id}`,
      {
        name: NameAndSurname,
        email: email,
        role: "NORMAL",
      },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (res?._embedded != undefined) {
      // ! put address
      await axios.put(
        `${baseUrl}/addresses/${res?._embedded?.addresses[0].id}`,
        {
          street,
          number,
          city: location,
          state: contact,
          country: floor,
          zipCode: postalCode,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      dispatch(
        setAddress(
          street +
            " " +
            number +
            " " +
            floor +
            " " +
            location +
            " " +
            postalCode
        )
      );
      console.log(floor);
    }

    // if (canSave) {
    //   try {
    //     e.preventDefault();
    //     console.log("entro");
    //     await addNewDataUser(id, {
    //       name: "NUEVO NOMBRE SEBAS",
    //       email: "sebastian.gomz02@gmail.com",
    //       role: "NORMAL",
    //       password: "123456",
    //     });
    //     history.push(`/users/${id}`);
    //   } catch (error) {
    //     console.error("fail to save put ", error);
    //   }
    // }
    // // alert("Guardando");
  };

  let content;
  if (isFetching) {
    content = <Loader />;
  } else if (isSuccess) {
    content = (
      <>
        <MyDataStyle>
          <form onSubmit={submit}>
            <label htmlFor="name">Nombre y apellido</label>
            <br />
            <input
              placeholder="Ingrese su nombre y apellido"
              required
              type="text"
              id="name"
              onChange={(e) => setNameAndSurname(e.target.value)}
              value={NameAndSurname}
            />
            <br />
            <div className="postalCode_location">
              <label htmlFor="postalCode">Codigo postal</label>
              <div className="location">
                <label htmlFor="location">Localidad</label>
              </div>
            </div>
            <input
              required
              placeholder="Ingrese el codigo postal"
              type="text"
              id="postalCode"
              onChange={(e) => setPostalCode(e.target.value)}
              value={postalCode}
            />
            <input
              required
              placeholder="Ingrese su localidad (ciudad)"
              type="text"
              id="location"
              onChange={(e) => setLocation(e.target.value)}
              value={location}
            />
            <br />
            <div className="address">
              <div className="street">
                <label htmlFor="street">Calle</label>
              </div>
              <div className="number">
                <label htmlFor="number">Número</label>
              </div>
              <div className="piso">
                <label htmlFor="floor">Piso</label>
              </div>
            </div>
            <input
              placeholder="Ingrese la direccion de su domicilio"
              required
              type="text"
              id="street"
              onChange={(e) => setStreet(e.target.value)}
              value={street}
            />
            <input
              placeholder="Ingrese el numero"
              required
              type="text"
              id="number"
              onChange={(e) => setNumber(e.target.value)}
              value={number}
            />
            <input
              placeholder="Ingrese el numero"
              type="text"
              id="floor"
              onChange={(e) => setFloor(e.target.value)}
              value={floor}
            />
            <div className="contact_email">
              <label htmlFor="contact">Contacto</label>
              <div className="email">
                <label htmlFor="email">Correo electronico</label>
              </div>
            </div>
            <input
              placeholder="Ingrese su numero de telefono"
              required
              type="text"
              id="contact"
              onChange={(e) => setContact(e.target.value)}
              value={contact}
            />
            <input
              placeholder="Ingrese su correo electronico"
              required
              type="text"
              id="email"
              onChange={(e) => setEmail(e.target.value)}
              value={email}
            />
            <div className="moreDetails">
              <label htmlFor="moreDetails">Aclaracion (opcional)</label>
            </div>
            <input
              placeholder="Deje una aclaracion de su domicilio aqui"
              type="text"
              id="moreDetails"
              onChange={(e) => setmoreDetails(e.target.value)}
              value={moreDetails}
            />
            <div className="button">
              <button>Guardar</button>
            </div>
          </form>
        </MyDataStyle>
      </>
    );
  }
  return content;
}
