import { useEffect, useState } from "react";
import { Outlet, useParams } from "react-router-dom";
import { HeaderSubTitle } from "../../../../components";
import { Div, SectionStyledBackground } from "../../../../styled-components";
import useListFood from "../hook/useListFood";
import RenderingFood from "./RenderingFood";
import fondoCafeteria from "/assets/img/fondoCafe.png"; //
import fondoChurrasqueria from "/assets/img/fondoCarnes.png";
import fondoPastas from "/assets/img/fondopastas.png";
import fondoPizza from "/assets/img/fondoPizza.png";
import fondoPostres from "/assets/img/fondoPostres.png";
import fondoSalusables from "/assets/img/fondoSaludables.png";

const ItemsCategory = () => {
  const { idCategory, idFood } = useParams();
  const { list, isLoading } = useListFood(idCategory);

  const [backgroundImage, setBackgroundImage] = useState(null);
  useEffect(() => {
    // Cargar imagen de fondo para la categoría actual
    if (idCategory === "Pastas") {
      setBackgroundImage(fondoPastas);
    } else if (idCategory === "Cafeteria") {
      setBackgroundImage(fondoCafeteria);
    } else if (idCategory === "Churrasqueria") {
      setBackgroundImage(fondoChurrasqueria);
    } else if (idCategory === "Pizzas") {
      setBackgroundImage(fondoPizza);
    } else if (idCategory === "Postres") {
      setBackgroundImage(fondoPostres);
    } else if (idCategory === "Saludables") {
      setBackgroundImage(fondoSalusables);
    } else {
      setBackgroundImage(null);
    }
  }, [idCategory]);

  return (
    <SectionStyledBackground img={backgroundImage} size="cover">
      {!idFood ? (
        <>
          <HeaderSubTitle
            title={` ${idCategory}`}
            textAlign="center"
            level={2}
          />
          <Div blockSize="100vh" inlineSize="100%" gap="120px" padding="7em 0">
            <RenderingFood isLoading={isLoading} list={list} />
          </Div>
        </>
      ) : (
        <Outlet />
      )}
    </SectionStyledBackground>
  );
};

export default ItemsCategory;
