var gulp = require('gulp');
var connect = require('gulp-connect');
var del = require('del');
var uglify = require('gulp-uglify');
var usemin = require('gulp-usemin');
var minifyHtml = require('gulp-minify-html');
var rev = require('gulp-rev');

gulp.task('clean', function (cb) {
    del([
        // everything inside the dist folder
        'src/main/webapp/dist/**/*'
    ], cb);
});

gulp.task('usemin', function () {
    return gulp.src('src/main/webapp/*.html')
        .pipe(usemin({
            html: [minifyHtml({empty: true, conditionals:true})],
            js: [uglify(), 'concat', rev()]
        }))
        .pipe(gulp.dest('src/main/webapp/dist'));
});

gulp.task('server', function() {
    connect.server({
        livereload: true,
        root: './src/main/webapp'
    });
});

gulp.task('html', function(){
    gulp.src('./src/main/webapp/**/*.html').pipe(connect.reload());
});

gulp.task('watch', function() {
   gulp.watch(['./src/main/webapp/**/*.html'], ['html']);
});

gulp.task('serve', ['server', 'watch']);

gulp.task('build', ['clean', 'usemin']);