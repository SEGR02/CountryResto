import {CardList, Footer, Header, ItemFood,} from "../../../components";
import Input from "../../home/components/Input";
import {Div} from "../../../styled-components"
import {ButtonSearchStyled, FormStyled,} from "../../home/styled-components/layout.styled";

import useSearch from "../../home/hook/useSearch";
import NavSide from "../../../components/NavSide";
import search from "/assets/icons/search.svg";
import {NavLink} from "react-router-dom";
import {MessageSearchStyled, SearchContainerStyled} from "../styled-components/SearchPage.styled";

const ContentSearchPage = () => {
    const {
        active,
        handleActive,
        handleDesActive,
        textSearch,
        handleOnchange,
        template,
        isSuccess,
    } = useSearch();
    const handleOnSubmit = (event) => {
        event.preventDefault();
    };

    return (
        <>
            <NavSide/>
            <Header/>
            <CardList/>
            <FormStyled onSubmit={handleOnSubmit}
                        searchpage={"/"}>
                <Input
                    name={"q"}
                    type={"search"}
                    onFocus={handleActive}
                    onChange={handleOnchange}
                    value={textSearch}/>
                <ButtonSearchStyled img={search}/>
            </FormStyled>

            <SearchContainerStyled>
                <Div gap={"7em"} padding='4em 0'>
                    {isSuccess ? (
                        <>
                            {template.length === 0 ?
                                <MessageSearchStyled>
                                    No hay publicaciones que coincidan con tu búsqueda. <br/>
                                    Te invitamos a <NavLink to={"/categoria"}>
                                    navegar por las categorías</NavLink> para encontrar el plato que buscas.
                                </MessageSearchStyled> :
                                <>
                                    {template.map((item) => {
                                        return <ItemFood
                                            key={item.id}
                                            id={item.id}
                                            nameFood={item.name}
                                            img={item.image.url}
                                            description={item.description}
                                            portion={item.portionPerUnit}
                                            price={item.price}
                                            category={item.category.name}
                                        />
                                    })
                                    }</>
                            }
                        </>
                    ) : (
                        <div
                            style={{
                                height: "50vh",
                                margin: "4em auto",
                                padding: "0 2em",
                                maxWidth: "530px",
                                textAlign: "center"
                            }}>loading...</div>
                    )
                    }
                </Div>
            </SearchContainerStyled>
            <Footer/>
        </>
    )
}

export default ContentSearchPage;
