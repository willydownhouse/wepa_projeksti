import { fetchComments, addComment, deleteTweet, modifyDate } from "./utils.js";

const allCommentsButtons = document.querySelectorAll("#commentsBtn");
const allCommentFormWrappers = document.querySelectorAll(".commentFormWrapper");

const addCommenttForms = document.querySelectorAll(".addCommentForm");

const allDeleteTweetBtns = document.querySelectorAll("#deleteTweetBtn");

let clickedTweet;

//DELETE TWEET

allDeleteTweetBtns.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    var url = contextRoot + `tweets/${e.target.value}`;
    deleteTweet(url);
  });
});

// SHOW COMMENTS
let numOfCommentsValue;

allCommentsButtons.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    const commentWrapperEl = e.target.closest(".row").nextElementSibling;
    // const commentWrapperEl = e.target
    //   .closest(".card-body")
    //   .querySelector(".commentFormWrapper");

    const commentListEl = e.target
      .closest(".row")
      .nextElementSibling.querySelector("#commentList");
    // const commentListEl =
    //   e.target.nextElementSibling.querySelector("#commentList");

    //CommentNum
    const numOfCommentsEl = e.target
      .closest(".row")
      .querySelector("#numOfComments");

    numOfCommentsEl.innerText = "";

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
      createdAt: modifyDate(),
    };

    const commentsListElement = e.target.previousElementSibling;

    addComment(url, commentObject, commentsListElement);

    e.target.querySelector(".form-control").value = "";
  });
});

console.log("daas");
