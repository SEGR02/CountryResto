import React from "react";
import { useState } from "react";
import { MyCardsStyle } from "../../../styled-components/MyAccountComponent";

const MisTarjetas = () => {
  const [NameAndSurname, setNameAndSurname] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [cvv, setCvv] = useState("");
  const [expirationDate, setExpirationDate] = useState("");

  return (
    <>
      <MyCardsStyle>
        <form action="">
          <p>Medios de pago</p>
          <div className="radio">
            <div className="debito">
              <input
                required
                type="radio"
                name="card"
                id="debit"
                value="debit"
              />
              <label className="labeldebit" htmlFor="debit">
                Débito
              </label>
            </div>
            <div className="credito">
              <input
                required
                type="radio"
                name="card"
                id="credit"
                value="credit"
              />
              <label className="labelcredit" htmlFor="credit">
                Crédito
              </label>
            </div>
          </div>
          <label htmlFor="name">Nombre y apellido</label>
          <input
            required
            placeholder="Ingrese su nombre y apellido"
            type="text"
            id="name"
            onChange={(e) => setNameAndSurname(e.target.value)}
            value={NameAndSurname}
          />
          <div className="card">
            <label htmlFor="cardNumber">Numero de tarjeta</label>
            <div className="cvv">
              <label htmlFor="cvv">CVV</label>
            </div>
          </div>
          <input
            required
            placeholder="Ingrese los 12 digitos"
            type="text"
            id="cardNumber"
            onChange={(e) => setCardNumber(e.target.value)}
            value={cardNumber}
          />
          <input
            required
            placeholder="Ingrese el codigo de seguridad"
            maxlength="3"
            id="cvv"
            onChange={(e) => setCvv(e.target.value)}
            value={cvv}
            type="password"
          />
          <div className="expDate">
            <label htmlFor="expirationDate">Fecha de vencimiento</label>
          </div>
          <input
            required
            placeholder="MM/YY"
            type="text"
            id="expirationDate"
            onChange={(e) => setExpirationDate(e.target.value)}
            value={expirationDate}
          />
          <div className="button">
            <button>Agregar</button>
          </div>
        </form>
      </MyCardsStyle>
    </>
  );
};

export default MisTarjetas;
