import { CardList, Footer, Header } from "../../../components";
import NavSide from "../../../components/NavSide";
import useSearch from "../hook/useSearch";
import Main from "./Main";
import SearchLayer from "./SearchLayer";
const MainContent = () => {
	const {
		active,
		handleDesActive,
		template,
		isSuccess,
		handleActive,
		textSearch,
		handleOnchange,
	} = useSearch();
	if(active === true){
		document.documentElement.style.setProperty('--hidden', "hidden");
	} else{
		document.documentElement.style.setProperty('--hidden', "auto")
	}

	console.log(active);
	return (
		<>
			<NavSide />
			<Header />
			<CardList />
			<Main
				active={active}
				handleActive={handleActive}
				handleOnchange={handleOnchange}
				textSearch={textSearch}
			/>
			<SearchLayer
				template={template}
				active={active}
				isSuccess={isSuccess}
				handleDesActive={handleDesActive}
			/>
			<Footer />
		</>
	);
};

export default MainContent;
