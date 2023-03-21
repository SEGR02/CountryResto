import { Outlet, useParams } from "react-router-dom";
import { CardList, Footer, Header } from "../../../../components";
import NavSide from "../../../../components/NavSide";
import Section from "./Section";

const ContentCategory = () => {
	const { idCategory } = useParams();
	return (
		<>
			<NavSide />
			<Header />
			<CardList />
			{!idCategory ? <Section /> : <Outlet />}
			<Footer />
		</>
	);
};

export default ContentCategory;
