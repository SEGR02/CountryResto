import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { ThxContainer } from "./CheckoutStyled.jsx";
import Loader from "./Loader.jsx";
import img from "../../../public/assets/cart-check.svg";
const Thanks = () => {
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setIsLoading(false);
    }, 2000);
  }, []);

  return (
    <ThxContainer>
      {isLoading ? (
        <Loader />
      ) : (
        <div>
          <h1>Gracias por su compra</h1>
          <br />
          <img src={img} alt="Carrito" />
          <br />
          <h2>Orden #123RGR231567Y Confirmado</h2>
          <Link to={"/"}>
            <button>Seguir Comprando</button>
          </Link>
        </div>
      )}
    </ThxContainer>
  );
};

export default Thanks;
