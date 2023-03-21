import { ItemsLoader } from "../../../components";
import Items from "../../../components/Items";
import { useFilterDishes } from "../../../hooks";
import {
  ButtonCarruselStyled,
  CarruselStyled,
} from "../styled-components/layout.styled";
import { foodSpecials } from "../utilities/foodSpecials";
import left from "/assets/icons/circleLeft.svg";
import rigth from "/assets/icons/circleRigth.svg";

const Carrusel = ({ move, handleMoveLeft, handleMoveRight }) => {
  const { list, isSuccess } = useFilterDishes(foodSpecials);
  console.log(list);
  return (
    <CarruselStyled translateX={`${move}%`}>
      <ButtonCarruselStyled
        img={left}
        // size="cover"
        insetBlock="41%"
        insetInline="1%"
        onClick={handleMoveLeft}
      />
      <ButtonCarruselStyled
        img={rigth}
        size="cover"
        insetBlock="41%"
        insetInline="96%"
        onClick={handleMoveRight}
      />
      <div>
        {isSuccess ? (
          list.map((item) => (
            <Items
              key={item.id}
              id={item.id}
              title={item.name}
              image={item.image.url}
              description={item.description}
              price={item.price}
              category={item.category.name}
              people={item.people}
              hasPromotion={item.hasPromotion}
            />
          ))
        ) : (
          <>
            <ItemsLoader
              borderRadius="29px"
              blockSize="192px"
              inlineSize="300px"
              top="-28%"
              insetInline="8%"
            />
            <ItemsLoader
              borderRadius="29px"
              blockSize="192px"
              inlineSize="300px"
              top="-28%"
              insetInline="8%"
            />
            <ItemsLoader
              borderRadius="29px"
              blockSize="192px"
              inlineSize="300px"
              top="-28%"
              insetInline="8%"
            />
            <ItemsLoader
              borderRadius="29px"
              blockSize="192px"
              inlineSize="300px"
              top="-28%"
              insetInline="8%"
            />
          </>
        )}
      </div>
    </CarruselStyled>
  );
};

export default Carrusel;
