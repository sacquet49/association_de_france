<?php
namespace App\Controller;

use App\Entity\Association;
use App\Entity\WaldecAssociation;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use FOS\RestBundle\View\View;
use FOS\RestBundle\Controller\Annotations as FOSRest;
use App\Entity\Nouvelle;
use \Datetime;

/**
 * Brand controller.
 *
 * @Route("/api")
 */
class NouvelleController extends AbstractController
{
    /**
     * @FOSRest\Get("/nouvelles")
     *
     */
    public function getNouvelles(Request $request)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $nouvelles = $entityManager->getRepository(Nouvelle::class)->findAll();
        return View::create($nouvelles, Response::HTTP_OK , []);
    }

    /**
     * @Security("is_granted('ROLE_SUPER_ADMIN')")
     * @FOSRest\Post("/auth/admin/nouvelle")
     *
     */
    public function saveNouvelles(Request $request)
    {
        $new = new Nouvelle();
        $new->setTitre($request->request->get('titre'));
        $new->setDescription($request->request->get('description'));
        $new->setDateCreation(new DateTime());

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($new);
        $entityManager->flush();

        return View::create($new, Response::HTTP_OK , []);
    }

    /**
     * @Security("is_granted('ROLE_SUPER_ADMIN')")
     * @FOSRest\Delete("/auth/admin/nouvelle/{id}")
     *
     */
    public function removeNouvelles(Request $request)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $new = $entityManager->getRepository(Nouvelle::class)->find($request->get('id'));
        $entityManager->remove($new);
        $entityManager->flush();

        return View::create($new, Response::HTTP_OK , []);
    }
}