//import { fetchComments, addComment } from "./utils";

const overlay = document.querySelector(".overlay");
const myModal = document.querySelector(".myModal");
const newTweetBtn = document.getElementById("newTweet");
const allCommentsButtons = document.querySelectorAll("#commentsBtn");
const allCommentFormWrappers = document.querySelectorAll(".commentFormWrapper");
const addCommenttForms = document.querySelectorAll(".addCommentForm");

let clickedTweet;

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
      createdAt: new Date().toISOString(),
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

//////////////////////////////
export const fetchComments = (url, el) => {
  fetch(url)
    .then((res) => res.json())
    .then((data) => {
      console.log(data);

      if (data.length === 0) {
        el.innerHTML =
          "<p class='text-muted'><small>This tweet does not have any comments yet.</small></p>";
      } else {
        el.innerHTML = buildCommentElement(data);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

export const addComment = (url, obj, el) => {
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(obj),
  })
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      //fetch after adding
      fetchComments(url, el);
    })
    .catch((err) => {
      console.log(err);
    });
};

const buildCommentElement = (data) => {
  const html = data
    .map((comment) => {
      return `<li class="list-group-item border-0">
                <div class="card border-0">
                  <div class="card-body">
                    <h6 class="card-subtitle mb-2 text-muted">
                      @${comment.account.username}
                    </h6>
                    <p class="card-text">${comment.text}</p>
                    <h6
                      id="date"
                      class="card-subtitle mb-2 text-muted"
                    ><small>${comment.createdAt.split("T")[0]}
                    ${comment.createdAt.split("T")[1].substring(0, 5)}</small>
                      
                    </h6>
                  </div>
                </div>
              </li>`;
    })
    .join(" ");

  return html;
};
