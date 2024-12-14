export class News {
    newsId: number;
    newsCategory: string;
    newsTitle: string;
    newsDescription: string;
    newsContent: string;
    newsURL: string;
    newsImage: string;
    newsPublishedAt: string;  // Using string because of the LocalDateTime format.
    newsAuthor: string;
    newsSource: string;
    newsCountry: string;
    newsLikes: number;
    newsDislikes: number;
    newsPremium: String;

    constructor(
        newsId: number,
        newsCategory: string,
        newsTitle: string,
        newsDescription: string,
        newsContent: string,
        newsURL: string,
        newsImage: string,
        newsPublishedAt: string,
        newsAuthor: string,
        newsSource: string,
        newsCountry: string,
        newsLikes: number,
        newsDislikes: number,
        newsPremium: String
    ) {
        this.newsId = newsId;
        this.newsCategory = newsCategory;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsContent = newsContent;
        this.newsURL = newsURL;
        this.newsImage = newsImage;
        this.newsPublishedAt = newsPublishedAt;
        this.newsAuthor = newsAuthor;
        this.newsSource = newsSource;
        this.newsCountry = newsCountry;
        this.newsLikes = newsLikes;
        this.newsDislikes = newsDislikes;
        this.newsPremium = newsPremium;
    }
}
