import { useState } from "react";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { useAddNewPostMutation } from "../../../../redux/query/FoodInfo.query";
const useComment = () => {
  const [comment, setComment] = useState("");
  const { idFood } = useParams();
  const token = useSelector((state) => state.auth.accessToken);
  const [addNewPost, { status }] = useAddNewPostMutation();
  const handleChange = (event) => {
    setComment(event.target.value);
  };

  const post = {
    id: Number(idFood),
    token,
    content: JSON.stringify({ content: comment }),
  };

  const onSavePost = async () => {
    if (post.content.comment !== "") {
      await addNewPost(post, token).unwrap();
      setComment("");
    }
  };

  return {
    comment,
    onSavePost,
    handleChange,
  };
};

export default useComment;
