import styled from "styled-components";

export const MyAccountStyle = styled.div`
  background-color: #2d2d2d;
  width: 100%;
  border-radius: 0;
  padding: 70px 90px;

  p {
    font-size: x-large;
    margin: 0;
    color: #fff;
    margin-bottom: 41px;
  }

  label {
    color: #c3c2c4;
  }

  button {
    font-size: large;
    font-family: var(--font-family);
    color: #fff;
  }

  .line {
    border: 0.5px solid #fff;
    // margin: 41px 0px 64px 0px;
  }

  .line2 {
    border: 0.5px solid #fff;
    margin-right: 35px;
  }

  .selection {
    display: flex;
    flex-flow: column;
  }

  .selection div {
    display: flex;
  }

  img {
    width: 19px;
    height: auto;
  }
  .container {
    display: flex;
    widht: 100%;
    justify-content: space-between;
  }

  .apartados {
    margin-top: 64px;
    width: 15%;
  }

  button:hover,
  .apartados div:hover {
    cursor: pointer;
    color: #fcdb9b;
  }

  #mascara {
    display: inline-block;
    width: 22px;
    height: 22px;
    background: url(https://res.cloudinary.com/doxahduh8/image/upload/v1677045499/usersvg_oitghj.svg);
  }

  @supports (-webkit-mask: url("")) or (mask: url("")) {
    #mascara:hover {
      -webkit-mask: url(https://res.cloudinary.com/doxahduh8/image/upload/v1677045499/usersvg_oitghj.svg);
      mask: url(https://res.cloudinary.com/doxahduh8/image/upload/v1677045499/usersvg_oitghj.svg);
      mask-size: cover;
      background: #fcdb9b;
    }
  }

  .data {
    margin-top: 64px;
    width: 80%;
  }

  .apartados > div {
    margin-bottom: 10px;
  }

  .postalCode_localidad {
    height: fit-content;
  }
`;

export const MyDataStyle = styled.div`
  input {
    width: 100%;
    height: 40px;
    border: solid #acacac 1.75px;
    border-radius: 5px;
    padding-left: 25px;
  }

  .postalCode_location,
  .address {
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
  }

  .contact_email {
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
  }

  .moreDetails {
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
  }

  .location {
    margin-right: 42%;
  }

  .number {
    margin-left: 27.5%;
  }

  .piso {
    margin-right: 21.5%;
  }

  .email {
    margin-right: 35.5%;
  }

  #postalCode,
  #location,
  #street,
  #contact,
  #email {
    width: 49.65%;
  }

  #postalCode,
  #street,
  #number,
  #contact {
    margin-right: 5px;
  }

  #number,
  #floor {
    width: 24.5%;
  }

  button {
    border-radius: 4px;
    background-color: #ffa800;
    color: black;
    font-family: "Poppins";
    font-weight: 600;
    font-size: 20px;
    padding: 10px 70px;
    margin-top: 50px;
    cursor: pointer;
  }

  .button {
    display: flex;
    justify-content: flex-end;
    cursor: pointer;
  }

  button:hover {
    background-color: #ffc451;
    color: black;
    cursor: pointer;
  }
`;

export const MyCardsStyle = styled.div`
  p {
    color: white;
    margin: 0;
  }

  .radio {
    margin: 15px 0 20px 0;
    display: flex;
  }

  .debito {
    margin-right: 45px;
    display: flex;
  }

  .credito {
    display: flex;
  }

  .debito input[type="radio"],
  .credito input[type="radio"] {
    -ms-transform: scale(1.5); /* IE 9 */
    -webkit-transform: scale(1.5); /* Chrome, Safari, Opera */
    transform: scale(1.5);
  }

  div > input[type="radio"] {
    display: none;
  }

  div > input[type="radio"] + *::before {
    content: "";
    display: inline-block;
    vertical-align: bottom;
    width: 1rem;
    height: 1rem;
    margin-right: 0.3rem;
    border-radius: 50%;
    border-style: solid;
    border-width: 0.1rem;
    border-color: #ffa800;
    margin-bottom: 3px;
  }

  div > input[type="radio"]:checked + *::before {
    background: radial-gradient(
      #ffa800 0%,
      #ffa800 40%,
      transparent 50%,
      transparent
    );
    border-color: #ffa800;
  }

  input[type="text"],
  input[type="password"] {
    width: 100%;
    height: 40px;
    border: solid #acacac 1.75px;
    border-radius: 5px;
    padding-left: 25px;
  }

  #cardNumber,
  #cvv,
  #expirationDate {
    width: 49.65%;
  }

  .card,
  .expDate {
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
  }

  #cardNumber {
    margin-right: 5px;
  }

  .cvv {
    margin-right: 46.5%;
  }

  button {
    border-radius: 4px;
    background-color: #ffa800;
    color: black;
    font-family: "Poppins";
    font-weight: 600;
    font-size: 20px;
    padding: 10px 70px;
    margin-top: 50px;
    cursor: pointer;
  }

  .button {
    display: flex;
    justify-content: flex-end;
  }

  button:hover {
    background-color: #ffc451;
    color: black;
    cursor: pointer;
  }
`;

export const MyOrdersStyle = styled.div`
  .titularPedidos {
    font-size: 15px;
    color: #acacac;
    padding: 20px 0 0px 25px;
  }

  .descripcionPedidos {
    padding: 20px 0 0px 25px;
    font-size: 15px;
  }
`;
export const LogOurStyle = styled.div`
  .contenedorLogOut {
    padding: 20px 0 0px 25px;
  }
  label {
    font-size: 16px;
  }

  input {
    width: 446px;
    height: 40px;
    border: solid #acacac 1.75px;
    background-color: #acacac;
    border-radius: 5px;
    padding-left: 25px;
    margin-bottom: 10px;
  }

  button {
    border-radius: 4px;
    background-color: #ffa800;
    color: black;
    font-weight: 600;
    font-size: 20px;
    padding: 10px 70px;
    cursor: pointer;
  }

  .button {
    display: flex;
    justify-content: flex-end;
  }

  button:hover {
    background-color: #ffc451;
    color: black;
    cursor: pointer;
  }
`;
