import { AuthModalStyled } from "../styled-components/layout/layout.styled";

function AuthModal({ isOpen, children }) {
	return <>{isOpen && <AuthModalStyled>{children}</AuthModalStyled>}</>;
}

export default AuthModal;
