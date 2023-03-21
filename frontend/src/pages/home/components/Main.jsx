import CustomerFeedback from "./CustomerFeedback";
import SectionHero from "./SectionHero";
const Main = ({ active, handleActive, textSearch, handleOnchange }) => {
	return (
		<main>
			<SectionHero
				handleActive={handleActive}
				textSearch={textSearch}
				onChange={handleOnchange}
				active={active}
			/>
			<CustomerFeedback />
			{/* 			<SearchLayer
				template={template}
				active={active}
				isSuccess={isSuccess}
				handleDesActive={handleDesActive}
			/> */}
		</main>
	);
};

export default Main;
