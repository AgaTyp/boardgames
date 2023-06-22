package pl.agntyp.boardgames;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
public class BoardgameController {
    private final AgeRangeRepository ageRangeRepository;
    private final BoardgameRepository boardgameRepository;
    private final CategoryRepository categoryRepository;
    private final StatsRepository statsRepository;
    private final PlayerRepository playerRepository;
    private final PlayerRangeRepository playerRangeRepository;

    public BoardgameController(AgeRangeRepository ageRangeRepository, BoardgameRepository boardgameRepository,
                               CategoryRepository categoryRepository, StatsRepository statsRepository,
                               PlayerRepository playerRepository, PlayerRangeRepository playerRangeRepository) {
        this.ageRangeRepository = ageRangeRepository;
        this.boardgameRepository = boardgameRepository;
        this.categoryRepository = categoryRepository;
        this.statsRepository = statsRepository;
        this.playerRepository = playerRepository;
        this.playerRangeRepository = playerRangeRepository;
    }

    @GetMapping("/fragments")
    public String fragments(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "fragments";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Boardgame> lastPlayedGames = boardgameRepository.findAllByOrderByLastTimePlayedDesc();
        List<Boardgame> newestGames = boardgameRepository.findAllByOrderByCreationDateDesc();

        model.addAttribute("categories", categories);
        model.addAttribute("lastPlayedGame", lastPlayedGames.get(0));
        model.addAttribute("newestGame", newestGames.get(0));
        return "home";
    }

    @GetMapping("/category")
    public String listCategory(@RequestParam(defaultValue = "0") long id, Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Boardgame> boardgames;
        String categoryName = "wszystkie";
        if (id != 0L) {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent()) {
                boardgames = boardgameRepository.findBoardgameByCategory(category);
                categoryName = category.get().getName();
            } else {
                boardgames = null;
            }

        } else {
            boardgames = boardgameRepository.findAll();
        }
        model.addAttribute("categories", categories);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("boardgames", boardgames);
        return "category";
    }

    @GetMapping("/info")
    public String gameInfo(@RequestParam long id, Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Stats> statsWinners;
        Map<String, Integer> winners = new LinkedHashMap<>();
        List<Stats> statsPlays;
        List<Stats> statsPoints;

        Optional<Boardgame> boardgame = boardgameRepository.findById(id);
        if (boardgame.isPresent()) {
            statsWinners = statsRepository.findAllByBoardgameOrderByWinCounterDesc(boardgame);
            statsWinners.forEach(stats -> {
                winners.put(playerRepository.findByStats(stats).getName(), stats.getWinCounter());
            });
//            winners = playerRepository.findAllByStatsIn(statsWinners);
            statsPlays = statsRepository.findAllByBoardgameOrderByPlayCounterDesc(boardgame);
            statsPoints = statsRepository.findAllByBoardgameOrderByMaxPointsDesc(boardgame);

        } else {
            statsWinners = null;
//            winners = null;
            statsPlays = null;
            statsPoints = null;
        }

        model.addAttribute("categories", categories);
        model.addAttribute("statsWinners", statsWinners);
        model.addAttribute("winners", winners);
        model.addAttribute("statsPlayers", statsPlays);
        model.addAttribute("statsPoints", statsPoints);

        model.addAttribute("boardgame", boardgame.get());
        return "info";
    }

    @GetMapping("/add_new_boardgame")
    public String viewAddPage(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("newGame", new Boardgame());
        model.addAttribute("newAgeRange", new AgeRange());
        model.addAttribute("newPlayerRange", new PlayerRange());
        model.addAttribute("newCategory", new Category());
        return "add_new_boardgame";
    }

    @PostMapping("/addCategory")
    public String addNewCategory(Category category) {
        if (categoryRepository.findByName(category.getName()) == null) {
            categoryRepository.save(category);
        }
        return "redirect:/add_new_boardgame";
    }

    @PostMapping("/add")
    public String addNewGame(Boardgame boardgame) {
        if (ageRangeRepository.findByMinAge(boardgame.getAgeRange().getMinAge()) == null) {
            AgeRange ageRange1 = new AgeRange();
            ageRange1.setMinAge(boardgame.getAgeRange().getMinAge());
            ageRangeRepository.save(ageRange1);
            boardgame.setAgeRange(ageRange1);
        } else {
            boardgame.getAgeRange().setId(ageRangeRepository.findByMinAge(boardgame.getAgeRange().getMinAge()).getId());
        }

        if (playerRangeRepository.findByMinPlayersAndMaxPlayers(boardgame.getPlayerRange().getMinPlayers(),
                boardgame.getPlayerRange().getMaxPlayers()) == null) {
            PlayerRange playerRange1 = new PlayerRange();
            playerRange1.setMinPlayers(boardgame.getPlayerRange().getMinPlayers());
            playerRange1.setMaxPlayers(boardgame.getPlayerRange().getMaxPlayers());
            playerRangeRepository.save(playerRange1);
            boardgame.setPlayerRange(playerRange1);
        } else {
            boardgame.getPlayerRange().setId(playerRangeRepository.findByMinPlayersAndMaxPlayers(boardgame.getPlayerRange().getMinPlayers(),
                    boardgame.getPlayerRange().getMaxPlayers()).getId());
        }

        if (boardgameRepository.findByTitle(boardgame.getTitle()) == null) {
            boardgame.setCreationDate(LocalDate.now());
            boardgameRepository.save(boardgame);
        }
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String gameEdit(@RequestParam long id, Model model) {
        List<Category> categories = categoryRepository.findAll();

        Optional<Boardgame> boardgame = boardgameRepository.findById(id);

        model.addAttribute("categories", categories);
        model.addAttribute("imageFileName", boardgame.get().getImageFile());

        model.addAttribute("boardgame", boardgame.get());
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(Boardgame boardgame) {

        boardgame.getAgeRange().setId(ageRangeRepository.findByMinAge(boardgame.getAgeRange().getMinAge()).getId());
        boardgame.setAgeRange(boardgame.getAgeRange());
        boardgame.getCategory().setId(categoryRepository.findByName(boardgame.getCategory().getName()).getId());
        boardgame.setCategory(boardgame.getCategory());
        boardgame.getPlayerRange().setId(playerRangeRepository.findByMinPlayersAndMaxPlayers(boardgame.getPlayerRange().getMinPlayers(),
                boardgame.getPlayerRange().getMaxPlayers()).getId());
        boardgame.setPlayerRange(boardgame.getPlayerRange());
        boardgame.setMinTime(boardgame.getMinTime());
        boardgame.setMaxTime(boardgame.getMaxTime());
        boardgame.setDescription(boardgame.getDescription());
        boardgame.setImageFile(boardgame.getImageFile());
        boardgame.setLastTimePlayed(boardgame.getLastTimePlayed());
        boardgame.setCreationDate(boardgame.getCreationDate());
        boardgame.setMyFavorite(boardgame.isMyFavorite());
        boardgame.setMyOwn(boardgame.isMyOwn());
        boardgame.setTitle(boardgame.getTitle());

        boardgameRepository.save(boardgame);

        return "redirect:/info?id=" + boardgame.getId();
    }

    @PostMapping("/deleteGame")
    public String deleteGame(Boardgame boardgame) {
        Category category = boardgameRepository.findById(boardgame.getId()).get().getCategory();
        boardgameRepository.delete(boardgame);
        return "redirect:/category?id=" + category.getId();
    }

}
