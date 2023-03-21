import { CommentContainer, CommentImg, CommentName, Comment, CommentDate } from "../styled-components"

const ComentsItems = ({ image, name, comment, date }) => {
  return (
    <CommentContainer>
      <CommentImg>
        <img src={image} alt="image" />
      </CommentImg>
      <div>
        <CommentName>{name}</CommentName>
        <Comment>{comment}</Comment>
        <CommentDate>{date}</CommentDate>
      </div>
    </CommentContainer>
  )
}

export default ComentsItems