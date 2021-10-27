import { fetchComments, addComment, deleteTweet } from "./utils.js";

const overlay = document.querySelector(".overlay");
const myModal = document.querySelector(".myModal");
const newTweetBtn = document.getElementById("newTweet");
const allCommentsButtons = document.querySelectorAll("#commentsBtn");
const allCommentFormWrappers = document.querySelectorAll(".commentFormWrapper");
const addCommenttForms = document.querySelectorAll(".addCommentForm");
const allDeleteTweetBtns = document.querySelectorAll("#deleteTweetBtn");

let clickedTweet;

//DELETE TWEET

allDeleteTweetBtns.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    var url = contextRoot + `tweets/${e.target.value}`;
    deleteTweet(url, e.target.value);
  });
});

// SHOW COMMENTS

allCommentsButtons.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    const commentWrapperEl = e.target
      .closest(".card-body")
      .querySelector(".commentFormWrapper");

    const commentListEl =
      e.target.nextElementSibling.querySelector("#commentList");

    const tweetId = e.target.getAttribute("value");

    //hide all comments
    allCommentFormWrappers.forEach((el) => el.classList.add("displayNone"));

    //close comments
    if (clickedTweet === tweetId) {
      clickedTweet = null;
      return;
    }

    //open clicked comments
    commentWrapperEl.classList.remove("displayNone");
    clickedTweet = tweetId;

    //FETCH ALL COMMENTS
    var url = contextRoot + `tweets/${clickedTweet}/comments`;

    fetchComments(url, commentListEl);
  });
});

//ADD NEW COMMENT

addCommenttForms.forEach((form) => {
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    let comment = e.target.querySelector(".form-control").value;

    var url = contextRoot + `tweets/${clickedTweet}/comments`;

    const commentObject = {
      text: comment,
      createdAt: new Date().toUTCString(),
    };

    const commentsListElement = e.target.previousElementSibling;

    addComment(url, commentObject, commentsListElement);

    e.target.querySelector(".form-control").value = "";
  });
});

// NEW TWEET FORM

newTweetBtn.addEventListener("click", () => {
  overlay.classList.remove("hidden");
  myModal.classList.remove("hidden");
  myModal.classList.add("showModal");
});
overlay.addEventListener("click", () => {
  overlay.classList.add("hidden");
  myModal.classList.add("hidden");
  myModal.classList.remove("showModal");
});
