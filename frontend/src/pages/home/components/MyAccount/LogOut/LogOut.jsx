import React from "react";
import { LogOurStyle } from "../../../styled-components/MyAccountComponent";

export default function () {
  return (
    <LogOurStyle>
      <div className="contenedorLogOut">
        <label htmlFor="name">Nombre y apellido</label>
        <br />
        <input type="text" placeholder="Ingrese su nombre" id="name" />
        <br />
        <label htmlFor="email">Correo Electronico</label>
        <br />
        <input type="text" placeholder="Ingrese su correo" id="email" />
        <br />
        <label htmlFor="password">Contraseña</label>
        <br />
        <input type="password" placeholder="Ingrese su clave" id="password" />
        <br />
        <div className="button">
          <button>Cerrar Sesión</button>
        </div>
      </div>
    </LogOurStyle>
  );
}
