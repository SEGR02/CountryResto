import { HeaderSubTitle } from "../../../components"
import CommentLisContainer from "./CommentLisContainer"


const CommentsSection = () => {
  return (
    <section style={{ margin: "6em 3em 0", display: "grid" }}>
      <HeaderSubTitle
        title='Lo que dicen nuestros clientes'
        textAlign='center'
        level={2}
      />
      <CommentLisContainer />
    </section>
  )
}

export default CommentsSection