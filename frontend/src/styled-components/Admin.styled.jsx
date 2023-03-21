import styled from "styled-components";

export const AdminCreateStyle = styled.div`
  width: 100vw;
  height: 100vh;
  // background-color: #2d2d2d;
  nav {
    height: 10%;
    display: flex;
    justify-content: space-around;
    gap: 60%;
    background-color: black;
    padding: 20px 0;
  }
  .administrator {
    display: flex;
    align-items: center;
  }
  object {
    margin-right: 7px;
  }
  .container {
    display: flex;
    height: 90%;
    background-color: #2d2d2d;
  }
  .list {
    width: 15%;
    display: flex;
    flex-direction: column;
    align-items: right;
    padding-left: 30px;
    padding-top: 20px;
    gap: 0.3%;
    p {
      font-weight: 500;
    }
  }
  .component {
    width: 85%;
    padding-top: 20px;
    background-color: black;
  }
  .secciones {
    text-align: left;
    font-size: x-large;
    font-family: var(--font-family);
    color: #fff;
    margin: 0 0;
  }
  button {
    text-align: left;
    font-size: large;
    font-family: var(--font-family);
    color: #fff;
    padding-left: 20%;
    :hover {
      cursor: pointer;
      color: #fcdb9b;
    }
  }
`;

export const DishesListStyles = styled.div`
  .head {
    display: flex;
    justify-content: space-around;
    gap: 40%;
    align-items: center;
  }
  p {
    font-size: 1.5em;
  }
  .select {
    position: relative;
    max-height: 100%;
    border-radius: 4px;
    border: 2px solid #86818b;
    margin-right: 3%;
  }
  select {
    background-color: transparent;
    color: #acacac;
    text-align: left;
    cursor: pointer;
    padding: 7px 210px 7px 10px;
    height: 42px;
    font-size: 1em;
    border-right: 15px solid transparent;
    border-radius: 4px;
    transition: all 0.25s ease;
    background-image: url(https://res.cloudinary.com/doxahduh8/image/upload/v1677544115/fleca_uciv8y.png);
    background-repeat: no-repeat;
    background-position: right center;
    -webkit-appearance: none;
    -moz-appearance: none;
    -o-appearance: none;
    appearance: none;
  }
  select::-ms-expand {
    display: none;
  }
  .items-container {
    padding: 0 50px;
  }
  .line {
    width: 95%;
    border-bottom: 1px solid white;
  }
  .item {
    display: flex;
    justify-content: space-between;
  }
  .item-icons {
    display: flex;
    align-items: center;
    gap: 15%;
  }
  .dish {
    margin: 15px 0;
    font-size: 1em;
    flex-direction: row;
  }
  svg:hover {
    cursor: pointer;
    fill: orange;
  }
`;

export const CategoryListStyles = styled.div`
  margin-left: 50px;
  .createCategory {
    width: 100%;
  }
  .tituloListado {
    font-size: 24px;
    font-weight: 500;
  }
  .listaListado {
    font-size: 15px;
    font-weight: 400;
  }
  .listadoCategoria {
    width: 100%;
  }
  .iconoCategorias {
    height: 25px;
    width: auto;
  }
  .listadoCatDiv {
    display: flex;
    flex-flow: row;
    justify-content: space-between;
    align-items: center;
  }
  .inputCategoria {
    width: 446px;
    height: 40px;
    border: solid #acacac 1.75px;
    background-color: transparent;
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
    width: 228px;
    height: 50px;
    float: right;
  }
  .line {
    width: 95%;
    border-bottom: 1px solid white;
  }
  .item {
    display: flex;
    justify-content: space-between;
  }
  .item-icons {
    display: flex;
    align-items: center;
    gap: 15%;
  }
  .dish {
    margin: 15px 0;
    font-size: 1em;
    flex-direction: row;
  }
  svg:hover {
    cursor: pointer;
    fill: orange;
  }
  .name {
    height: 40px;
    border-radius: 5px;
    padding-left: 10px;
    width: 97%;
  }
  .inputImage {
    display: inline-block;
    border: 2px solid black;
    width: 60%;
    background-color: white;
    padding: 1px 2px 1px 10px;
    justify-content: center;
    color: #757575;
    margin-right: 15px;
    font-size: 12px;
    display: flex;
    align-items: center;
    height: 40px;
    border-radius: 5px;
    padding-left: 10px;
    img {
      padding-left: 62%;
    }
  }
  button {
    border-radius: 4px;
    width: fit-content;
    background-color: #ffa800;
    color: black;
    font-family: "Poppins";
    font-weight: 600;
    font-size: 20px;
    padding: 10px 140px 10px 140px;
    cursor: pointer;
  }
  .button {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 30px;
  }
  .image_price {
    display: flex;
    align-items: center;
  }
`;

export const CreateDishStyles = styled.div`
  margin: 0 35px;
  p {
    font-size: 1.5em;
  }
  .inputImage {
    display: inline-block;
    border: 2px solid black;
    width: 49.65%;
    background-color: white;
    padding: 1px 2px 1px 10px;
    justify-content: center;
    color: #757575;
    margin-right: 15px;
    font-size: 12px;
    display: flex;
    justify-content: start;
    align-items: center;
    img {
      padding-left: 62%;
    }
  }
  input[type="text"],
  .category,
  .inputImage {
    height: 40px;
    border-radius: 5px;
    padding-left: 10px;
  }
  .name,
  .description,
  .category {
    width: 100%;
  }
  .description,
  .image_import,
  .categoryDiv {
    margin-top: 20px;
  }
  .image_import {
    display: flex;
    justify-content: space-between;
  }
  .price {
    width: 49.65%;
  }
  .category {
    background-image: url(https://res.cloudinary.com/doxahduh8/image/upload/v1677544115/fleca_uciv8y.png);
    background-repeat: no-repeat;
    background-position: right center;
    -webkit-appearance: none;
    -moz-appearance: none;
    -o-appearance: none;
    appearance: none;
    border-right: 15px solid transparent;
  }
  .image_price {
    display: flex;
  }
  button {
    border-radius: 4px;
    background-color: #ffa800;
    color: black;
    font-family: "Poppins";
    font-weight: 600;
    font-size: 20px;
    padding: 20px 150px;
    cursor: pointer;
  }
  .container {
    display flex;
    flex-direction: row;
    background-color: black;
    margin-top: 40px;
  }
  .checkbox {
    display: flex;
    align-items: center;
    margin-right: 40px;
    width: 100%;
    justify-content: left;
    height: 100%;
    align-items: end;
  }
  .checkbox span {
    margin-right: 20px;
  }
  .import {
    margin-right: 43.5%;
  }
  .checkboxInput {
    display: none;
  }
  span {
    width: 18px;
    height: 18px;
    display: block;
    background-color: transparent;
    border-radius: 1px;
    border: 2px solid #ffc451;
  }
  input:checked + span {
    background-color: #ffc451;
    background-image: url(https://res.cloudinary.com/doxahduh8/image/upload/v1677569493/icon_sktlb7.png);
    background-position: center;
    background-repeat: no-repeat;
  }
  .promotions {
    width: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .discount {
    width: 70%;
    display: flex;
    flex-direction: column;
  }
  discountInput {
    width: 50%;
  }
  .button{
    width: 50%;
    display: flex;
    justify-content: end;
  }
`;