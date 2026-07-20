import { useState } from "react";
import API from "../services/api";

function ReviewForm({ userId, productId }) {

    const [rating, setRating] = useState("");
    const [reviewComment, setReviewComment] = useState("");

    const addReview = async () => {

        if (!userId) {
            alert("Enter User ID");
            return;
        }

        if (!rating) {
            alert("Enter Rating");
            return;
        }

        try {

            // Get OrderItem for this user and product
            const orderItemResponse = await API.get(
                `/order-items/user/${userId}/product/${productId}`
            );

            const orderItemId = orderItemResponse.data.orderItemId;

            // Add Review
            await API.post(
                `/reviews/add/${userId}/${orderItemId}`,
                {
                    rating,
                    reviewComment
                }
            );

            alert("Review Added Successfully");

            setRating("");
            setReviewComment("");

            window.location.reload();

        } catch (error) {

            console.log(error);

            if (error.response) {
                alert(error.response.data.message || "Unable to add review");
            } else {
                alert("Something went wrong");
            }
        }

    };

    return (

        <div style={{ marginTop: "30px" }}>

            <h2>Add Review</h2>

            <div>

                <label>Rating (1-5)</label>

                <br />

                <input
                    type="number"
                    min="1"
                    max="5"
                    value={rating}
                    onChange={(e) => setRating(e.target.value)}
                />

            </div>

            <br />

            <div>

                <label>Comment</label>

                <br />

                <textarea
                    rows="4"
                    cols="50"
                    value={reviewComment}
                    onChange={(e) => setReviewComment(e.target.value)}
                />

            </div>

            <br />

            <button onClick={addReview}>
                Submit Review
            </button>

        </div>

    );

}

export default ReviewForm;